# gdb-python patches from https://github.com/crash-python/gdb-python

FILESEXTRAPATHS_prepend := "${THISDIR}/gdb:"

SRC_URI += " \
    file://0001-check_types_equal-short-circuit-check-if-identical-p.patch \
    file://0002-gdb-properly-zero-out-memory-used-for-fields-when-al.patch \
    file://0003-python-use-utf-8-encoding-for-gdb_write.patch \
    file://0004-gdb-exclude-PROP_UNDEFINED-ranges-from-bound_flag_ev.patch \
    file://0005-symfile-delay-new-objfile-event-until-after-relocati.patch \
    file://0006-py-value-properly-handle-unsigned-and-pointer-types.patch \
    file://0007-py-symbol-use-Py_RETURN_NONE-instead-of-open-coding-.patch \
    file://0008-py-symbol-Allow-gdb_lookup_symbol-to-accept-block-No.patch \
    file://0009-py-type-gdbpy_lookup_type-accept-None-as-NULL.patch \
    file://0010-py-objfile-export-objfile-gdbarch.patch \
    file://0011-py-minsymbol-Add-interface-to-access-minimal_symbols.patch \
    file://0012-py-register-Add-interface-to-registers.patch \
    file://0013-pyblock-add-dictionary-browsing-for-symbols.patch \
    file://0014-target-add-delete_target.patch \
    file://0015-py-target-add-interface-to-target.patch \
    file://0016-py-target-local-testing-code.patch \
    file://0017-py-target-don-t-do-target_preopen-or-reopen_exec_fil.patch \
    file://0018-py-target-pass-thread-with-register-for-fetch_regist.patch \
    file://0019-py-thread-add-interface-to-create-threads-and-associ.patch \
    file://0020-py-inferior-py-infthread-add-executing-attribute.patch \
    file://0021-py-type-use-non-deep-comparison-for-typy_richcompare.patch \
    file://0022-terrible-symfile-hack.patch \
    file://0023-py-objfile-add-has_symbols-method.patch \
    file://0024-gdb-python-add-gdb.NotAvailableError-exception.patch \
    "
